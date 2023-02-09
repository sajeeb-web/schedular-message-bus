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

package bd.gov.dgfood.food.grain.license.scheduler.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;

import java.util.TimeZone;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


@Component(
	    immediate = true,
	    service = SchedulerServiceImpl.class
	)
public class SchedulerServiceImpl{
	public void createSchedule(String jobName, String groupName, String cron, String destinationName, String description) throws SchedulerException {

		//Trigger trigger = TriggerFactoryUtil.createTrigger(jobName,groupName,cronExpression);
		Trigger trigger = _triggerFactory.createTrigger(jobName,groupName,null,null,cron,TimeZone.getDefault());
		Message message = new Message();
		message.put("data",jobName+":"+groupName);
		//SchedulerEngineHelperUtil.schedule(trigger, StorageType.PERSISTED, description,destinationName, message, exceptionsMaxSize)
		_schedulerEngineHelper.schedule(trigger, StorageType.PERSISTED, description, destinationName, message);

	}
	
	public static int exceptionsMaxSize = 10;
	
	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;
	
	@Reference
	private TriggerFactory _triggerFactory;
	
	@SuppressWarnings("unused")
	private static final Log _log = LogFactoryUtil.getLog(SchedulerServiceImpl.class);
}
