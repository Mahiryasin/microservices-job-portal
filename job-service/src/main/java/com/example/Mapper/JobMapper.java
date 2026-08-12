package com.example.Mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.example.DTO.JobDTO;
import com.example.Entity.Job;
import com.example.util.AppUtil;

@Component
public class JobMapper {
    private final AppUtil appUtil;

    JobMapper(AppUtil appUtil) {
        this.appUtil = appUtil;
    }

    public Job toJob(JobDTO jobDTO) {
        return Job.create(
            jobDTO.getId(),
                jobDTO.getDescription(),
                jobDTO.getCompany(),
                jobDTO.getSkills(),
                jobDTO.getSalary(),
                jobDTO.getIsRemote());
    }

    public JobDTO toJobDTO(Job job) throws Exception {
        JobDTO jobDTO=new JobDTO();
        BeanUtils.copyProperties(job, jobDTO);
        jobDTO.setHostName(appUtil.getHostName());

        return jobDTO;
}
}
