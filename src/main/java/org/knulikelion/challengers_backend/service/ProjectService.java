package org.knulikelion.challengers_backend.service;

import org.knulikelion.challengers_backend.data.dto.request.ProjectRequestDto;
import org.knulikelion.challengers_backend.data.dto.response.AllProjectResponseDto;
import org.knulikelion.challengers_backend.data.dto.response.BaseResponseDto;
import org.knulikelion.challengers_backend.data.dto.response.ProjectResponseDto;
import org.knulikelion.challengers_backend.data.dto.response.ResultResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProjectService {
    Object getProjectById(Long id);
    Page<AllProjectResponseDto> getAllProjects(int page, int size);
    BaseResponseDto removeProject(Long id);
    BaseResponseDto createProject(ProjectRequestDto projectRequestDto, String token);
    BaseResponseDto updateProject(Long id, ProjectRequestDto projectRequestDto, String token);
}
