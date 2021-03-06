package com.intern.Internship.utils;

import com.intern.Internship.model.Feedback;
import com.intern.Internship.model.Internship;
import com.intern.Internship.model.dto.ApplicationDTO;
import com.intern.Internship.model.dto.FeedbackDTO;
import com.intern.Internship.model.dto.InternshipDTO;
import com.intern.Internship.model.dto.PageDTO;
import org.springframework.data.domain.Page;

import java.util.*;

public class Converters {
    public static PageDTO<InternshipDTO> mapPageToInternshipDTOPage(Page<Map<String, ?>> page) {
        PageDTO<InternshipDTO> pageDTO = new PageDTO<>();
        List<InternshipDTO> internshipDTOS = new ArrayList<>();
        for (Map<String, ?> map : page.getContent()) {
            InternshipDTO internshipDTO = new InternshipDTO(map);
            internshipDTOS.add(internshipDTO);
        }
        return getInternshipDTOPageDTO(pageDTO, internshipDTOS, page.hasNext(), page.hasPrevious(),
                page.getTotalPages(), page);
    }

    public static PageDTO<InternshipDTO> internshipPageToInternshipDTOPage(Page<Internship> page) {
        PageDTO<InternshipDTO> pageDTO = new PageDTO<>();
        List<Internship> internships = page.getContent();
        List<InternshipDTO> internshipDTOS = new ArrayList<>();

        for (Internship internship1 : internships) {
            InternshipDTO internshipDTO = new InternshipDTO(internship1);
            internshipDTOS.add(internshipDTO);
        }
        return getInternshipDTOPageDTO(pageDTO, internshipDTOS, page.hasNext(), page.hasPrevious(),
                page.getTotalPages(), page);
    }

    public static List<InternshipDTO> internshipPageToInternshipDTOPage(List<Internship> internships) {
        List<InternshipDTO> internshipDTOS = new ArrayList<>();

        for (Internship internship1 : internships) {
            InternshipDTO internshipDTO = new InternshipDTO(internship1);
            internshipDTOS.add(internshipDTO);
        }
        return internshipDTOS;
    }

    private static <T> PageDTO<InternshipDTO> getInternshipDTOPageDTO(PageDTO<InternshipDTO> pageDTO,
            List<InternshipDTO> internshipDTOS, boolean hasNext, boolean hasPrevious, int totalPages, Page<T> page) {
        pageDTO.setContent(internshipDTOS);
        pageDTO.setHasNext(hasNext);
        pageDTO.setHasPrevious(hasPrevious);
        pageDTO.setNbPages(totalPages);
        return pageDTO;
    }

    public static Internship dtoToInternshipUpdate(Internship intern, InternshipDTO internDTO) {
        intern.setName(internDTO.getName() == null ? intern.getName() : internDTO.getName());
        intern.setStartTime(internDTO.getStartTime() == null ? intern.getStartTime() : internDTO.getStartTime());
        intern.setEndTime(internDTO.getEndTime() == null ? intern.getEndTime() : internDTO.getEndTime());
        intern.setPaid(internDTO.getPaid() == null ? intern.getPaid() : internDTO.getPaid());
        intern.setNrMonths(internDTO.getNrMonths() == 0 ? intern.getNrMonths() : internDTO.getNrMonths());
        intern.setDescription(
                internDTO.getDescription() == null ? intern.getDescription() : internDTO.getDescription());
        intern.setNrApplicants(
                internDTO.getNrApplicants() == 0 ? intern.getNrApplicants() : internDTO.getNrApplicants());
        intern.setStatus(internDTO.getStatus() == null ? intern.getStatus() : internDTO.getStatus());
        intern.setLocation(internDTO.getLocation() == null ? intern.getLocation() : internDTO.getLocation());
        intern.setAddedDate(internDTO.getAddedDate() == null ? intern.getAddedDate() : internDTO.getAddedDate());

        return intern;
    }

    public static Internship toInternship(InternshipDTO internshipDTO) {
        Internship internship = new Internship();
        internship.setName(internshipDTO.getName());
        internship.setStartTime(internshipDTO.getStartTime());
        internship.setEndTime(internshipDTO.getEndTime());
        internship.setPaid(internshipDTO.getPaid());
        internship.setNrMonths(internshipDTO.getNrMonths());
        internship.setDescription(internshipDTO.getDescription());
        internship.setNrApplicants(internshipDTO.getNrApplicants());
        internship.setStatus(internshipDTO.getStatus());
        internship.setLocation(internshipDTO.getLocation());
        internship.setAddedDate(internshipDTO.getAddedDate());
        internship.setLogo(internshipDTO.getLogo());
        return internship;
    }

    public static List<ApplicationDTO> applicationToApplicationDTO(List<Map<String,?>> applications) {
        List<ApplicationDTO> applicationsDTOS = new ArrayList<>();
        for (Map<String, ?> map : applications) {
            ApplicationDTO applicationDTO = new ApplicationDTO(map);
            applicationsDTOS.add(applicationDTO);
        }
        return applicationsDTOS;
    }

    public static Set<FeedbackDTO> feedbacktoFeedbackDTO(Set<Feedback> feedbackSet) {
        Set<FeedbackDTO> feedbackDTOS =new LinkedHashSet<>();
        for(Feedback feedback:feedbackSet){
            feedbackDTOS.add(Converters.toFeedbackDTO(feedback));
        }
        return feedbackDTOS;
    }

    private static FeedbackDTO toFeedbackDTO(Feedback feedback) {
        FeedbackDTO feedbackDTO=new FeedbackDTO();
        feedbackDTO.setID(feedback.getID());
        feedbackDTO.setAnonymous(feedback.getAnonymous());
        feedbackDTO.setDescription(feedback.getDescription());
        feedbackDTO.setRating(feedback.getRating());
        feedbackDTO.setInternshipId(feedback.getInternship().getID());

        return feedbackDTO;
    }

    public static Feedback feedbackDTOtoFeedback(FeedbackDTO feedbackDTO) {
        Feedback feedback =new Feedback();
        feedback.setRating(feedbackDTO.getRating());
        feedback.setDescription(feedbackDTO.getDescription());
        feedback.setAnonymous(feedbackDTO.getAnonymous());
        return feedback;
    }
}
