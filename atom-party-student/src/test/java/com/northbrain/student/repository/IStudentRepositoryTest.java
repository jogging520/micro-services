package com.northbrain.student.repository;

import com.northbrain.student.model.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class IStudentRepositoryTest {
    @Autowired
    private IStudentRepository studentRepository;

    @Before
    public void setUp() throws Exception {
        this.studentRepository.deleteAll();

        this.studentRepository
                .save(Student.builder().build());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findByStatusAndRegionIn() throws Exception {
        String[] regions = {"931", "932"};

        StepVerifier.create(
                this.studentRepository
                        .findByStatusAndRegionIn("ACTIVE", regions))
                .recordWith(ArrayList::new)
                .expectNextCount(2)
                .consumeRecordedWith(students -> {
                    assertThat(students.size(), is(3));
                })
        .expectComplete()
        .verify();
    }

    @Test
    public void findByStatusAndFamily() throws Exception {
    }

    @Test
    public void findByStatusAndSchool() throws Exception {
    }

    @Test
    public void findByNameAndFamily() throws Exception {
        StepVerifier.create(
                this.studentRepository
                .findByNameAndFamily("test", "testFamily"))
                .expectNextMatches(student -> {
                    assertThat(student.getName(), equalTo("test"));
                    assertThat(student.getFamily(), containsString("Family"));

                    return true;
                });
    }

}