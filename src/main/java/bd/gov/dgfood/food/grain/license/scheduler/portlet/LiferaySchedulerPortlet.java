package bd.gov.dgfood.food.grain.license.scheduler.portlet;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import bd.gov.dgfood.food.grain.license.scheduler.constants.LiferaySchedulerPortletKeys;
import bd.gov.dgfood.food.grain.license.service.DgfUsersLocalService;
import bd.gov.dgfood.food.grain.license.service.DgfUsersOtpDetailsLocalService;

/**
 * @author Sajeeb Ahmeed
 */

@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=LiferayScheduler", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + LiferaySchedulerPortletKeys.LIFERAYSCHEDULERE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)

public class LiferaySchedulerPortlet extends MVCPortlet {
	@Reference
	DgfUsersLocalService dgfUsersLocalService;
	DgfUsersOtpDetailsLocalService dgfUsersOtpDetailsLocalService;

	public void formdata(ActionRequest request, ActionResponse response) {
		CacheRegistryUtil.clear();

		String name = ParamUtil.getString(request, "name");
		String phoneNo = ParamUtil.get(request, "mobileNo", "user phone number");
		String nidNo = ParamUtil.get(request, "nidNo", "user nid number");
		dgfUsersLocalService.addDgfUsersInfo(name, phoneNo, nidNo);

	}
}