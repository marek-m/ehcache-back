package antre.gcm.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import antre.dao.DayDao;
import antre.dao.MealDao;
import antre.gcm.utils.Constants;
import antre.gcm.utils.Message;
import antre.gcm.utils.MulticastResult;
import antre.gcm.utils.Result;
import antre.gcm.utils.Sender;

@Service
public class GCMServiceImpl implements GCMService {

	private final Logger logger = Logger.getLogger(getClass().getName());
	private static final int MULTICAST_SIZE = 1000; // GOOGLE LIMIT FOR ONE MULTI SEND

	private static final String API_KEY = "AIzaSyBFhMf2MXkhxULud1pQ1NvMre4uV_Pfzv4";
	private static final Executor threadPool = Executors.newFixedThreadPool(5);
	private Sender sender;

	@Autowired
	private SessionFactory sf;

	@Autowired
	MealDao mealDao;

	@Autowired
	DayDao dayDao;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	/**
	 * Creates the {@link Sender} based on the servlet settings.
	 */
	private Sender newSender() {
		return new Sender(API_KEY);
	}

	// MOCK
	private List<String> getDevices() {
		List<String> result = new ArrayList<String>();
		result.add("APA91bFpmjfdN3DwR2SaXY4EqWalz_MItyG2DP2PXF4HoFpmjw68I6kuhAu9Ej1p9qGreN_NF12RBxcg8pUqlzjJZdvu5263PX5uekU-ZAlHhiHAvq7RTrZoFd-yxEj7nObvmrTnDSGTeMMRgx2TduZte5iOcvchZw");
		return result;
	}

	@Override
	public void test() {
		List<String> devices = getDevices();
		String status;
		if (devices.isEmpty()) {
			status = "Message ignored as there is no device registered!";
		} else {

			// send a multicast message using JSON
			// must split in chunks of 1000 devices (GCM limit)
			int total = devices.size();
			List<String> partialDevices = new ArrayList<String>(total);
			int counter = 0;
			int tasks = 0;
			for (String device : devices) {
				counter++;
				partialDevices.add(device);
				int partialSize = partialDevices.size();
				if (partialSize == MULTICAST_SIZE || counter == total) {
					asyncSend(partialDevices);
					partialDevices.clear();
					tasks++;
				}
			}
			status = "Asynchronously sending " + tasks
					+ " multicast messages to " + total + " devices";
		}
	}

	private void asyncSend(List<String> partialDevices) {
		if (sender == null)
			sender = newSender();

		// make a copy
		final List<String> devices = new ArrayList<String>(partialDevices);
		threadPool.execute(new Runnable() {

			public void run() {
				Message message = new Message.Builder().addData("TEST", "HELLO TEST MESSAGE").build();
				MulticastResult multicastResult;
				try {
					multicastResult = sender.send(message, devices, 5);
				} catch (IOException e) {
					logger.info("Error posting messages", e);
					return;
				}
				List<Result> results = multicastResult.getResults();
				// analyze the results
				for (int i = 0; i < devices.size(); i++) {
					String regId = devices.get(i);
					Result result = results.get(i);
					String messageId = result.getMessageId();
					if (messageId != null) {
						logger.info("Succesfully sent message to device: " + regId + "; messageId = " + messageId);
						String canonicalRegId = result.getCanonicalRegistrationId();
						if (canonicalRegId != null) {
							// same device has more than on registration id:
							// update it
							logger.info("canonicalRegId " + canonicalRegId);
							
							//TODO
							//Datastore.updateRegistration(regId, canonicalRegId);
						}
					} else {
						String error = result.getErrorCodeName();
						if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
							// application has been removed from device -
							// unregister it
							logger.info("Unregistered device: " + regId);
							
							//TODO
							//Datastore.unregister(regId);
						} else {
							logger.info("Error sending message to " + regId + ": " + error);
						}
					}
				}
			}
		});
	}
}
