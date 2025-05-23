package com.utadeo.miguelabarreram.simuladortransaccional.service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoMsgFldsTpl;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.controller.IsoMsgFldsTplController;
import com.utadeo.miguelabarreram.simuladortransaccional.tools.GenericJson;

@RestController
public class IsoMsgFldsTplServiceController {

	@Autowired
	IsoMsgFldsTplController isoMsgFldsTplController;
	
	@GetMapping("/isomsgfldstpl/getMsgTplFlds")
	public GenericJson getMsgTplFlds(@RequestParam Integer isoMsgTplId) {
		return new GenericJson(isoMsgFldsTplController.getTplFields(isoMsgTplId));
	}
	
	@PostMapping("/isomsgfldstpl/saveFldTpl")
	public IsoMsgFldsTpl saveFldTpl(@RequestBody IsoMsgFldsTpl isoMsgFldTpl) {
		return isoMsgFldsTplController.saveIsoMsgFldsTpl(isoMsgFldTpl);
	}
	
	@DeleteMapping("/isomsgfldstpl/removeFldTpl")
	public void deleteFldTpl(@RequestBody IsoMsgFldsTpl isoMsgFldTpl) {
		isoMsgFldsTplController.deleteIsoMsgFldTpl(isoMsgFldTpl);
	}
	
	@GetMapping("/isomsgfldstpl/getMsgTplFldsToSendTx")
	public GenericJson getMsgTplFldsToSendTx(@RequestParam Integer isoMsgTplId) {
		Iterable<IsoMsgFldsTpl> getTplFields = isoMsgFldsTplController.getTplFields(isoMsgTplId);
		getTplFields.forEach(field -> {
			if (field.isValAuto()) {
				field.setIsoMsgFldsTplVal(formatValue(field));
			}
		});
		return new GenericJson(getTplFields);
	}
	
	private String formatValue(IsoMsgFldsTpl isoMsgFldsTpl) {
		String newValue = "";
		try {
			String format = isoMsgFldsTpl.getIsoMsgFldsTplVal();
			String type = format.substring(0, 1);
			int start = format.indexOf("(");
			int end = format.indexOf(")");
			String pattern = format.substring(start + 1, end);
			switch (type) {
				case "N":
					double min = Double.valueOf(pattern.split("-")[0]);
					double max = Double.valueOf(pattern.split("-")[1]);
					DecimalFormat df = new DecimalFormat("0");
					newValue = df.format(((Math.random() * ((max + 1) - min)) + min));
					break;
				case "S": 
					start = pattern.split("-")[0].codePointAt(0);
					end = pattern.split("-")[1].codePointAt(0);
					
					if (start > end) {
						start = pattern.split("-")[0].toUpperCase().codePointAt(0);
						end = pattern.split("-")[1].toLowerCase().codePointAt(0);
					}
					
					String value = "";
					int length = isoMsgFldsTpl.getIsoFieldId().getIsoFieldLength();
					while (value.length() < length) {
						int res = Double.valueOf((Math.random() * ((end + 1) - start)) + start).intValue();
						if ((res >= 65 &&  res <= 90) || (res >= 97 &&  res <= 122)) {
							value += (char) res;
						}
					}
					break;
				case "T":
					LocalDateTime currentTime = LocalDateTime.now();
					newValue = currentTime.format(DateTimeFormatter.ofPattern(pattern));
					break;
				default:
					newValue = "UNKNOWN FORMAT!";
			}
			if (!"UNKNOWN FORMAT!".equals(newValue) && isoMsgFldsTpl.getIsoFieldId().isIsoFieldPad()) {
				newValue = paddingValue(newValue, type, isoMsgFldsTpl.getIsoFieldId().getIsoFieldLength(), isoMsgFldsTpl.getIsoFieldId().getIsoFieldTypePad());
			}
		} catch(Exception e) {
			newValue = "FORMAT ERROR!";
		}
		return newValue;
	}
	
	private String paddingValue(String value, String type, int length, String typePad) {
		String filler = " ";
		if ("N".equals(type)) {
			filler = "0";
		}
		while (value.length() < length) {
			if ("L".equals(typePad)) {
				value = filler + value;
			} else {
				value = value + filler;
			}
		}
		return value;
	}
	
}
