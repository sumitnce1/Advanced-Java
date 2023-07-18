package org.sumitexam.app.SumitFinalExam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sumitexam.app.SumitFinalExam.dto.MobilePhoneDTO;
import org.sumitexam.app.SumitFinalExam.services.MobilePhoneService;

import java.util.List;

@RestController
@RequestMapping("/api/mobile_0016")
public class MobilePhoneController 
{
	@Autowired
    MobilePhoneService mobilePhoneService;
    
    @PostMapping("/create_0016")
    public boolean createMobilePhone(@RequestBody MobilePhoneDTO mobilePhoneDTO) {
        return mobilePhoneService.createMobilePhone(mobilePhoneDTO);
    }

    @GetMapping("/getAllMobiles_0016")
    public List<MobilePhoneDTO> getAllMobiles() {
        return mobilePhoneService.getAllMobiles();
    }

    @GetMapping("/getMobileDetail_0016/{mobileid}")
    public MobilePhoneDTO getMobileDetail(@PathVariable("mobileid") int mobileId) {
        return mobilePhoneService.getMobileDetail(mobileId);
    }

    @PostMapping("/updateMobileDetail_0016/{mobileid}")
    public MobilePhoneDTO updateMobileDetail(@PathVariable("mobileid") int mobileId, @RequestBody MobilePhoneDTO mobilePhoneDTO) {
        return mobilePhoneService.updateMobileDetail(mobileId, mobilePhoneDTO);
    }
}
