
<%@ include file="/init.jsp" %>
<h2>Welcome to Liferay Message Bus</h2><br/>
<portlet:renderURL var="renderSendMessage">
	<portlet:param name="mvcRenderCommandName" value="/view/create_schedule" />
</portlet:renderURL>

<aui:button href="<%= renderSendMessage %>" value="Create Dynamic Schedule Jobs For DgFood" />

<portlet:actionURL name="formdata" var="formdata" />

<aui:form style="width:500px; margin: auto;"  align="center" action="<%=formdata%>" method="post">

    <aui:input  label="Your Name:" type="text"  name="name" />
   <aui:input label="nid no" name="nidNo"></aui:input>

  <aui:input label="Your phone number:" name="mobileNo"></aui:input>


    <aui:button type="submit" value ="ENTER"/>

</aui:form>