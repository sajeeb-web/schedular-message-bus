
<%@ include file="/init.jsp" %>
<portlet:actionURL name="/create/schedule" var="createScheduleURL" />
<%
	List<SelectOption> selectOptions = new ArrayList<>();
	selectOptions.add(new SelectOption("dgfood/parallel-destination", String.valueOf("dgfood/parallel-destination")));
	selectOptions.add(new SelectOption("dgfood/serial-destination", String.valueOf("dgfood/serial-destination")));
	selectOptions.add(new SelectOption("dgfood/synchronous-destination", String.valueOf("dgfood/synchronous-destination")));
%>

	
<aui:form action="<%= createScheduleURL %>" cssClass="edit-entry" enctype="multipart/form-data" method="post" name="fm">

		<div class="clearfix">
			<clay:select label="Destination" name="<%=curPortletNameSpace+"destination"%>" options="<%= selectOptions %>"/>
		</div>
	
		<div class="clearfix">
			<aui:input label="Cron Expression" name="<%=curPortletNameSpace+"cron"%>"  type="text" value="" />
		</div>

		<div class="clearfix">
			<aui:input label="Job Name" name="<%=curPortletNameSpace+"jobname"%>"  type="text" value="" />
		</div>
	
		<div class="clearfix">
			<aui:input label="Group Name" name="<%=curPortletNameSpace+"groupname"%>"  type="text" value="" />
		</div>
	
		<div class="clearfix">
			<aui:input label="Description" name="<%=curPortletNameSpace+"description"%>"  type="textarea" value="" />
		</div>

		<div class="clearfix">
			<aui:button name="saveButton" type="submit" value="Create Schedule Job" />
		</div>


</aui:form>


