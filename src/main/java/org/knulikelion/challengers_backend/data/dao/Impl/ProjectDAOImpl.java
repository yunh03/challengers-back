package org.knulikelion.challengers_backend.data.dao.Impl;

import org.knulikelion.challengers_backend.data.dao.ProjectDAO;
import org.knulikelion.challengers_backend.data.entity.Project;
import org.knulikelion.challengers_backend.data.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProjectDAOImpl implements ProjectDAO {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectDAOImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Optional<Project> selectProjectById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Page<Project> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    public void removeProject(Long id) {
        Project selectedProject = projectRepository.findById(id).get();
        selectedProject.setClub(null);
        selectedProject.setUser(null);
        projectRepository.delete(selectedProject);
    }

    @Override
    public void incrementViewCount(Long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if(optionalProject.isPresent()) {
            Project project = optionalProject.get();
            project.setViewCount(project.getViewCount() + 1);
            projectRepository.save(project);
        }
    }

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Project project) {
        Optional<Project> selectedProject = projectRepository.findById(project.getId());
        Project updatedProject;

        if(selectedProject.isPresent()) {
            updatedProject = projectRepository.save(project);
            return updatedProject;
        }

        return null;
    }
}
