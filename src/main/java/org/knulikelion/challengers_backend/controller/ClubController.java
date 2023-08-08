package org.knulikelion.challengers_backend.controller;

import org.knulikelion.challengers_backend.data.dto.request.ClubCreateRequestDto;
import org.knulikelion.challengers_backend.data.dto.request.ClubRequestDto;
import org.knulikelion.challengers_backend.data.dto.response.ResultResponseDto;
import org.knulikelion.challengers_backend.data.entity.ClubJoin;
import org.knulikelion.challengers_backend.data.entity.UserClub;
import org.knulikelion.challengers_backend.service.ClubJoinService;
import org.knulikelion.challengers_backend.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/club")
public class ClubController {
    private final ClubService clubService;
    private final ClubJoinService clubJoinService;

    @Autowired
    public ClubController(ClubService clubService, ClubJoinService clubJoinService) {
        this.clubService = clubService;
        this.clubJoinService = clubJoinService;
    }
    @GetMapping("/get")
    public Object getClubById(Long id){
        Object result = clubService.getClubById(id);
        return result;
    }
    @DeleteMapping("/remove")
    public ResultResponseDto removeClubById(Long id){
        return clubService.removeClub(id);
    }
    @PostMapping("/create")
    public ResultResponseDto createClub(@RequestBody ClubCreateRequestDto clubCreateRequestDto){
        return clubService.createClub(clubCreateRequestDto);
    }
    @PutMapping("/update")
    public ResultResponseDto updateClub(@RequestBody ClubRequestDto clubRequestDto , Long clubId) throws Exception {
        return clubService.updateClub(clubId,clubRequestDto);
    }
    @PostMapping("/addMember")
    public ResultResponseDto addClubMember(Long userId, Long clubId){
        return clubService.addMember(userId,clubId);
    }
    @PutMapping("/updateMember")
    public ResultResponseDto updateClubMember(Long userId, Long updateUserId, Long clubId){
        return clubService.updateMember(userId,updateUserId,clubId);
    }
    @DeleteMapping("/deleteMember")
    public ResultResponseDto removeClubMember(Long userId, Long clubId){
        return clubService.removeMember(userId,clubId);
    }

    @PostMapping("/join")
    public ClubJoin createJoinRequest(@RequestParam Long userId, @RequestParam Long clubId) {
        return clubJoinService.createJoinRequest(userId, clubId);
    }

    @PutMapping("/accpet-join-request")
    public UserClub acceptJoinRequest(@RequestParam("joinRequestId") Long joinRequestId){
        return clubJoinService.acceptJoinRequest(joinRequestId);
    }

}
