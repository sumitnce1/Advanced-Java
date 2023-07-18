package org.sumitexam.app.SumitFinalExam.services;

import org.sumitexam.app.SumitFinalExam.dto.MobilePhoneDTO;

import java.util.List;

public interface MobilePhoneService {
	boolean createMobilePhone(MobilePhoneDTO mobilePhoneDTO);
	List<MobilePhoneDTO> getAllMobiles();
	MobilePhoneDTO getMobileDetail(int mobileId);
	MobilePhoneDTO updateMobileDetail(int mobileId, MobilePhoneDTO mobilePhoneDTO);
}
