/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package bd.gov.dgfood.food.grain.license.scheduler.listener;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;

import com.liferay.portal.kernel.language.UTF8Control;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

import bd.gov.dgfood.food.grain.license.model.DgfUsersOtpDetails;
import bd.gov.dgfood.food.grain.license.service.DgfUsersOtpDetailsLocalServiceUtil;

/**
 * @author Sajeeb Ahmeed
 */

@Component(immediate = true, property = {
		"destination.name=dgfood/serial-destination" }, service = MessageListener.class)

public class AutomaticRegisteredSerialMessageListener implements MessageListener {
	private static final Log _log = LogFactoryUtil.getLog(AutomaticRegisteredParellelMessageListener.class);

	@Override
	public void receive(Message message) {
		CacheRegistryUtil.clear();

		try {

			// TODO send OTP and other action

			// read .properties value
			ResourceBundle _resourceBundle = ResourceBundle.getBundle("custom.application", UTF8Control.INSTANCE);

			String _teletalk_sms_bulk_url = _resourceBundle.getString("teletalk.sms.bulk.url");
			String _param_mobile = _resourceBundle.getString("teletalk.sms.mobile.param");
			String _bulk_url_sms_param = _resourceBundle.getString("teletalk.sms.user.sms.param");
			String _teletalk_bulk_UserProp = _resourceBundle.getString("teletalk.sms.user");
			String _bulk_PassParam = _resourceBundle.getString("teletalk.sms.parampass");
			String _bulk_PassProp = _resourceBundle.getString("teletalk.sms.pass");
			String _teletalk_sms_and_use = _resourceBundle.getString("teletalk.sms.and.use");
			String _teletalk_sms = _resourceBundle.getString("teletalk.sms.sms");
			String _bulk_charset = _resourceBundle.getString("teletalk.sms.charset");
			String _teletalk_sms_a_key = _resourceBundle.getString("teletalk.sms.a_key");
			String _teletalk_sms_p_key = _resourceBundle.getString("teletalk.sms.p_key");

			List<DgfUsersOtpDetails> dgfUsersOtpDetailsList = DgfUsersOtpDetailsLocalServiceUtil
					.getDgfUsersOtpDetailses(0, DgfUsersOtpDetailsLocalServiceUtil.getDgfUsersOtpDetailsesCount());

			for (DgfUsersOtpDetails UsersOtpDetailsInfoObj : dgfUsersOtpDetailsList) {

				String mobileNum = UsersOtpDetailsInfoObj.getMobileNo();
				int randomNumber = 1000 + (int) (Math.random() * 9000);

//				if (UsersOtpDetailsInfoObj.getOtpStatus() == false && UsersOtpDetailsInfoObj.getIsSent() == false) {
//
//					// TODO Checke the status and isSent then submit the message and update the
//					// table
//
//					String teletalk_bulk_message_url = _teletalk_sms_bulk_url + _param_mobile + mobileNum
//							+ _bulk_url_sms_param + _teletalk_bulk_UserProp + _bulk_PassParam + _bulk_PassProp
//							+ _teletalk_sms_and_use + randomNumber + _teletalk_sms + _bulk_charset + _teletalk_sms_a_key
//							+ _teletalk_sms_p_key;
//					URL url = new URL(teletalk_bulk_message_url);
//
//					// Create the connection
//					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//					// Set the request method and headers
//					connection.setRequestMethod("GET");
//					connection.setRequestProperty("Content-Type", "application/json");
//
//					// Send the request
//					connection.connect();
//
//					// Read the response
//					BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//					StringBuilder response = new StringBuilder();
//					String line;
//					while ((line = reader.readLine()) != null) {
//						response.append(line);
//					}
//					reader.close();
//
//					// Print the response
//					_log.info("Send Message::" + new Date() + response.toString() + message);
//
//					// TODO send OTP and set isSent status true
//					UsersOtpDetailsInfoObj.setOtp(randomNumber);
//					UsersOtpDetailsInfoObj.setIsSent(true);
//					UsersOtpDetailsInfoObj.setOtpRequestTime(new Date());
//					DgfUsersOtpDetailsLocalServiceUtil.updateDgfUsersOtpDetails(UsersOtpDetailsInfoObj);
//
//				}
			}

			_log.info("DgfoodFoodGrainSendOTP QUEUE 'Start' ");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}