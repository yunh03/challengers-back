package org.knulikelion.challengers_backend.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.knulikelion.challengers_backend.data.dto.request.ProjectRequestDto;
import org.knulikelion.challengers_backend.data.dto.response.AllProjectResponseDto;
import org.knulikelion.challengers_backend.data.dto.response.BaseResponseDto;
import org.knulikelion.challengers_backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/get")
    public Object getProjectById(Long id) {
        return projectService.getProjectById(id);
    }

    @GetMapping("/get/all")
    public List<AllProjectResponseDto> getAllProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {
        return projectService.getAllProjects(page, size);
    }

    @DeleteMapping("/remove")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "사용자 인증 Token", required = true, dataType = "String", paramType = "header")
    })
    public BaseResponseDto removeProjectById(Long id) {
        return projectService.removeProject(id);
    }

    @PostMapping("/create")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "사용자 인증 Token", required = true, dataType = "String", paramType = "header")
    })
    public BaseResponseDto createProject(@RequestBody ProjectRequestDto projectRequestDto, HttpServletRequest request) {
        return projectService.createProject(projectRequestDto, request.getHeader("X-AUTH-TOKEN"));
    }

    @PutMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "사용자 인증 Token", required = true, dataType = "String", paramType = "header")
    })
    public BaseResponseDto updateProject(@RequestBody ProjectRequestDto projectRequestDto, Long projectId, HttpServletRequest request) {
        return projectService.updateProject(projectId, projectRequestDto, request.getHeader("X-AUTH-TOKEN"));
    }
}