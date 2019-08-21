package responder.escuesta.formularios.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.service.DDLRecordLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

public class ActionUtil {
	
	//private static Log LOGGER = LogFactoryUtil.getLog(ActionUtil.class.getName());
	
	private List<String> hiddenFormField = null;

	/**
	 * This method get DDLrecord by ddmStructureId
	 * 
	 * @param ddmStructureId
	 * @return
	 * @throws PortalException
	 */
	public List<Map<String, String>> formFields(long ddmStructureId) throws PortalException {
		String data = DDMStructureLocalServiceUtil.getDDMStructure(ddmStructureId).getDefinition();
		JSONObject jsonObject = null;
		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String> map = null;
		jsonObject = JSONFactoryUtil.createJSONObject(data);
		JSONArray fields = jsonObject.getJSONArray("fields");
		map = new HashMap<String, String>();
		map.put("author", "Autor");
		//map.put("userId", "Id Usuario");		
		map.put("emailAdrress", "Correo");
		map.put("createDate", "Fecha Creación");		
		list.add(map);
		for (int i = 0; i < fields.length(); i++) {
			map = new HashMap<String, String>();
			JSONObject fieldsName = fields.getJSONObject(i);
			JSONObject localField = fieldsName.getJSONObject("label");
			if (!getHiddenField(localField.getString("es_ES").replace(":", ""))) {
				map.put(fieldsName.getString("name"), localField.getString("es_ES").replace(":", ""));
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * This method get DDMcontent by recordSetId
	 * 
	 * @param filedList
	 * @param recordSetId
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws PortalException
	 * @throws ParseException
	 */
	public List<String> formEntries(List<Map<String, String>> filedList, long recordSetId) throws PortalException, ParseException {

		String data = null;
		List<String> recordsList = new ArrayList<String>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		JSONObject jsonObject = null;
		for (DDLRecord record : getDDLRecords(recordSetId)) {
			data = DDMContentLocalServiceUtil.getDDMContent(record.getDDMStorageId()).getData();
		//	LOGGER.warn(">>>>>>>>>>>>>>>>>>>>>> data::  " + data);
		//	List<String> row = new ArrayList<String>();
		//  row.add(String.valueOf(record.getUserId()));
			
			String row = "";
			row = String.valueOf(record.getUserId());
			//row.add(record.getUserName().isEmpty()?"Guest":record.getUserName());
			//User user = UserLocalServiceUtil.getUserById(record.getUserId());
			//row.add(user.getEmailAddress());
			//row.add(dateFormat.format(record.getCreateDate()));
		//	row.add(String.valueOf(record.getUserId()));
			
			
			
//			jsonObject = JSONFactoryUtil.createJSONObject(data);
//			JSONArray fields = jsonObject.getJSONArray("fieldValues");
//			String key = "";
//			boolean isValueFound = false;
		//	LOGGER.warn(">>>>>>>>>>>>>>>>>>>>>> fields::  " + fields);
			
//			for (Map<String, String> field : filedList) {
//				key = (String) field.keySet().toArray()[0];
//				if (key != "author" && key != "createDate"  && key != "emailAdrress") {
//					isValueFound = false;
//					for (int i = 0; i < fields.length(); i++) {
//						if (Validator.isNotNull(fields.getJSONObject(i))) {
//							JSONObject fieldsValue = fields.getJSONObject(i);
//							JSONObject localFieldValue = fieldsValue.getJSONObject("value");
//							if (key.equals(fieldsValue.getString("name"))) {
//								row.add(localFieldValue.getString("es_ES").replace("[", "").replace("]", "")
//										.replace("\"", ""));
//								isValueFound = true;
//								break;
//							}
//						}
//					}
//					if (!isValueFound) {
//						row.add("");
//					}
//				}
//
//			}
			recordsList.add(row.toString());
		}
		return recordsList;
	}

	/**
	 * This method get DDLRecords by recordSetId And filtering record fromDate
	 * to toDate
	 * 
	 * @param recordSetId
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws ParseException
	 */
	private List<DDLRecord> getDDLRecords(long recordSetId) throws ParseException {
		Calendar cal = Calendar.getInstance();
		DynamicQuery dynamicQuery = DDLRecordLocalServiceUtil.dynamicQuery();
//		if (Validator.isNotNull(fromDate) && Validator.isNotNull(toDate)) {
//			cal.setTime(StringToDate(toDate));
//			cal.add(Calendar.DATE, +1);
//			Date todate = cal.getTime();
//			System.out.println("getDDLRecords>>>>fromDate: " + StringToDate(fromDate));
//			System.out.println("getDDLRecords>>>>todate: " + todate);
//			//dynamicQuery.add(PropertyFactoryUtil.forName("createDate").between(StringToDate(fromDate), todate));
//			Date startDate = StringToDate(fromDate);
//		//	Date endDate = todate;
//			Property startDateProperty = PropertyFactoryUtil.forName("startDate");
//		//	dq.add(startDateProperty.ge(startDate));
//		//	Property endDateProperty = PropertyFactoryUtil.forName("endDate");
//		//	Criterion criterion = PropertyFactoryUtil.forName("userId").eq(Long.parseLong("20155"));
//			Criterion criterion = PropertyFactoryUtil.forName("createDate").ge(startDate);
//			dynamicQuery.add(criterion);
//		}
		
		dynamicQuery.add(PropertyFactoryUtil.forName("recordSetId").eq(new Long(recordSetId)));
		return DDLRecordLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

	/**
	 * This method formating string date to date
	 * 
	 * @param searchDate
	 * @return
	 * @throws ParseException
	 */
	private static Date StringToDate(String inputDate) throws ParseException {
		Date formatDate = null;
		formatDate = new SimpleDateFormat("MM/dd/yyyy").parse(inputDate);
		return formatDate;
	}

	/**
	 * This method check hidden form field
	 * 
	 * @param fieldName
	 * @return
	 */
	private boolean getHiddenField(String fieldName) {
		if (hiddenFormField.contains(fieldName)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method set hidden form field
	 * 
	 * @param fields
	 */
	public void setHiddenField(String fields) {
		String fieldList[] = fields.split(",");
		this.hiddenFormField = Arrays.asList(fieldList);
	}
}
