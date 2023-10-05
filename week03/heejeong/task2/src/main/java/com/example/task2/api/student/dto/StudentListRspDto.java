package com.example.task2.api.student.dto;

import com.example.task2.api.common.PageInfoDto;
import com.example.task2.domain.student.Student;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StudentListRspDto {

    List<StudentDto> students;  // id, name의 목록
    PageInfoDto pageInfo;

    public static StudentListRspDto from(Page<Student> students) {
        List<StudentDto> studentDtoList = StudentDto.from(students);
        PageInfoDto pageInfoDto = PageInfoDto.from(students);
        return new StudentListRspDto(studentDtoList, pageInfoDto);
    }

    private StudentListRspDto(List<StudentDto> students, PageInfoDto pageInfo) {
        this.students = students;
        this.pageInfo = pageInfo;
    }

    @Getter
    @Builder
    static class StudentDto {
        long id;
        String name;

        static StudentDto from(Student student) {
            return StudentDto.builder()
                    .id(student.getId())
                    .name(student.getName())
                    .build();
        }

        static List<StudentDto> from(Page<Student> students) {
            return students.stream()
                    .map(student -> StudentDto.from(student))   // .map(StudentDto::from)과 동일
                    .collect(Collectors.toList());
        }
    }
}
