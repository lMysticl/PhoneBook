//package com.mystic.service;
//
//import com.mystic.model.entity.Contact;
//import org.junit.runner.RunWith;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.context.config.ResourceNotFoundException;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
///**
// * @author Pavel Putrenkov
// * @version 1.0
// * @since
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class RegistrationServiceImplTest {
//
//
//    public void setUp() {
//
//    }
//
//
//
//
//    @Transactional
//    @Rollback
//    public void deleteSubRequestTest() throws CannotDeleteRequestException, ResourceNotFoundException {
//        request = requestService.getRequestById(4L).get();
//        requestService.deleteRequestById(request.getId());
//        Assert.assertTrue(requestService.getRequestById(4L).isPresent());
//        Assert.assertEquals(requestService.getRequestById(4L).get().getStatus().getId(), new Integer(4));
//    }
//}
