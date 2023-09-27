package com.codingbottle.dbendpagination.domain.studentinlecture;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StudentInLecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
