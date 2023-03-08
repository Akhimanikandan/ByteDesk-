package com.bytedesk.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.assessment.bytedeskapp.service.Sample;
import com.assessment.bytedeskapp.serviceimpl.InfoServiceImpl;
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = SampleTest.class)
public class SampleTest{
	
	
	Sample sample = new Sample();

   @Test
	public void addNumbersTest(){
   int c= sample.addNumbers(3, 4);
    assertEquals(7, c);
	 

}}