package com.online.quiz.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.online.quiz.Exam;
import com.online.quiz.QuizQuestion;

/**
 * Servlet implementation class ExamController
 */
@WebServlet("/exam")
public class ExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doPost(request,response);
		//System.out.println(request);
		//System.out.println(response);
		//System.out.println(request.getAttribute("lol"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Initializing Exam not Finished
		String finish="0";
		
		//getting current Session
		HttpSession session=request.getSession();		
		try
		{
			if(session.getAttribute("currentExam")==null){
		     session=request.getSession(); 	
		     
		     //getting selected exam
		     String selectedExam=(String)request.getSession().getAttribute("exam");
		     
		     //Current Exam ID
		     String id=(String)request.getSession().getAttribute("id");
		     //System.out.println(id);
		     
		     //Total Number Of Questions
		     Object ob= session.getAttribute("totalNumberOfQuizQuestions");
		     
		     String sob=(String)ob;
			 Exam newExam=new Exam(selectedExam,id,Integer.parseInt(sob));
			 session.setAttribute("currentExam",newExam);
			 String sq=(String)request.getSession().getAttribute("totalNumberOfQuizQuestions");
			
			 newExam.setTotalNumberOfQuestions(Integer.parseInt(sq));
			 
			 /*
			  * Setting exam start Time
			  */
			 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a");
				Date date = new Date();
				String started=dateFormat.format(date);
			  session.setAttribute("started",started);
		  }
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	//Getting Current Exam from current session
        Exam exam=(Exam)request.getSession().getAttribute("currentExam");	
        //System.out.println("Exam is " + exam.getTitle());
        
        //Setting Exam Title
        request.getSession().setAttribute("examName", exam.getTitle());
        
        /*
         * Checking 
         * if Exam started
         * 	then setting
         * 		Exam Question No: 0
         */
        if(exam.currentQuestion==0){	
			exam.setQuestion(exam.currentQuestion);
		    QuizQuestion q=exam.questionList.get(exam.currentQuestion);	
			session.setAttribute("quest",q);
        }
			//System.out.println(request.getParameter("minute"));
			String action=request.getParameter("action");
//			System.out.println(action);
//			System.out.println(request.toString());
//			
//			Enumeration paramNames = request.getParameterNames();
//			while(paramNames.hasMoreElements()){
//				String paramName = (String)paramNames.nextElement();
//			      System.out.print("<tr><td>" + paramName + "</td>\n");
//			      String paramValue = request.getHeader(paramName);
//			      System.out.println("<td> " + paramValue + "</td></tr>\n");
//			}
			
			/*
			 * Handling Timer
			 */
			int minute=-1;
			int second=-1;
			if(request.getParameter("minute")!=null)
			{			
				minute=Integer.parseInt(request.getParameter("minute"));
				second=Integer.parseInt(request.getParameter("second"));
				request.getSession().setAttribute("min",request.getParameter("minute") );
				request.getSession().setAttribute("sec",request.getParameter("second") );
			}
			
			/*
			 * Get Answer
			 * for 
			 * Current Question
			 */
			String radio=request.getParameter("answer");
			int selectedRadio=-1;
			//System.out.println("beforelol"+exam.selections);
			//exam.selections.put(exam.currentQuestion, selectedRadio);
			//System.out.println("before"+exam.selections);
			//System.out.println(exam.getAnswerList());;
			
			//Initialising for getting marked Answers
			int cnt=0;
			
			
			//System.out.println("link"+request.getAttribute("link"));
			
			//Get Selected Question from Left Scrollable
			String link = request.getParameter("link");
			if(link!=null && !link.isEmpty()){
				exam.currentQuestion = Integer.parseInt(request.getParameter("link").toString());
				System.out.println("inlink"+exam.currentQuestion);
				System.out.println(exam.currentQuestion);
				exam.setQuestion(exam.currentQuestion-=1);
                                QuizQuestion q=exam.questionList.get(exam.currentQuestion);	
			  	session.setAttribute("quest",q);
			}
			
			//Perform Actions When Answer Submitted
			if("1".equals(radio))
			{
				//System.out.println("I am in 1");
				selectedRadio=1;
				//Setting Answered
				exam.setAnswered(exam.currentQuestion);
				//System.out.println("User selected set"+selectedRadio);
				//System.out.println("currentques"+exam.currentQuestion);
				//System.out.println("selections"+exam.questionList);
				
				//Setting option Selected
				exam.selections.put(exam.currentQuestion, selectedRadio);
				exam.questionList.get(exam.currentQuestion).setUserSelected(selectedRadio);				
			}
			else if("2".equals(radio))
			{
				System.out.println("I am in 2");
				selectedRadio=2;
				//Setting Answered
				exam.setAnswered(exam.currentQuestion);
				//System.out.println("User selected set"+selectedRadio);
				//System.out.println("currentques"+exam.currentQuestion);
				//System.out.println("selections"+exam.questionList);
				
				//Setting option Selected
				exam.selections.put(exam.currentQuestion, selectedRadio);
				exam.questionList.get(exam.currentQuestion).setUserSelected(selectedRadio);
			}
			else if("3".equals(radio))
			{
				System.out.println("I am in 3");
				selectedRadio=3;
				//Setting Answered
				exam.setAnswered(exam.currentQuestion);
				//System.out.println("User selected set"+selectedRadio);
				//System.out.println("currentques"+exam.currentQuestion);
				//System.out.println("selections"+exam.questionList);
				
				//Setting option Selected
				exam.selections.put(exam.currentQuestion, selectedRadio);
				exam.questionList.get(exam.currentQuestion).setUserSelected(selectedRadio);
			}
			else if("4".equals(radio))
			{
				System.out.println("I am in 4");
				selectedRadio=4;
				//Setting Answered
				exam.setAnswered(exam.currentQuestion);
				//System.out.println("User selected set"+selectedRadio);
				//System.out.println("currentques"+exam.currentQuestion);
				//System.out.println("selections"+exam.questionList);
				
				//Setting option Selected
				exam.selections.put(exam.currentQuestion, selectedRadio);
				exam.questionList.get(exam.currentQuestion).setUserSelected(selectedRadio);
			}
			//System.out.println("after"+exam.selections);
			//System.out.println(exam.currentQuestion);
//			if("action".equals(action)){
//				System.out.println(exam.currentQuestion);	
//			}
			
			//Clearing Current Option
			if("clear".equals(request.getParameter("clear"))){
				//System.out.println("quizlist"+exam.selections);
				exam.selections.put(exam.currentQuestion, -1);
                                exam.setUnAnswered(exam.currentQuestion);
			}

			//Gathering Count for all answers Questions
			for(int i=0;i<exam.getAnswerList().size();i++){
				if(exam.getAnswerList().get(i) != "-1"){
					cnt++;
				}
			}
			request.getSession().setAttribute("answercount", cnt);
			
			/*
			 * Suppress Warning
			 * Checking if marked 
			 */
			@SuppressWarnings("unused")
			String mark = request.getParameter("mark");
//			if(mark!=null && !mark.isEmpty()){
//				exam.setMarked(exam.currentQuestion);
//			}
			
			//Performing action when Marked
			if("Mark".equals(request.getParameter("mark"))){
				//System.out.println("markvalue"+request.getParameter("mark"));
				exam.setMarked(exam.currentQuestion);
			}
			//Performing action when Unmarked
			if("Unmark".equals(request.getParameter("unmark"))){
				//System.out.println("markvalue"+request.getParameter("unmark"));
				exam.setUnMarked(exam.currentQuestion);
			}
			request.getSession().setAttribute("mark",exam.getMarkedList());
			
			//System.out.println("mark"+exam.getMarkedList());
			
			
			//get Next Question
			if("Next".equals(action)){
				exam.currentQuestion++;
				//System.out.println("Clicked Next");
				exam.setQuestion(exam.currentQuestion);
			    QuizQuestion q=exam.questionList.get(exam.currentQuestion);	
			  	session.setAttribute("quest",q);
			  	//System.out.println("Current uestion:" +exam.currentQuestion);
			  	request.getSession().setAttribute("current", exam.currentQuestion);
			}
			
			//get previous Question
			else if("Previous".equals(action))
			{   exam.currentQuestion--;
				//System.out.println("Current Queston"+exam.currentQuestion);
				exam.setQuestion(exam.currentQuestion);
			    QuizQuestion q=exam.questionList.get(exam.currentQuestion);	
			    request.getSession().setAttribute("current", exam.currentQuestion);
				session.setAttribute("quest",q);
				
			}
			
			//Final Submission of exam
			else if("Finish Exam".equals(action)||( minute==0 && second==0))
			{   
				finish="1";			    
				//System.out.println(exam);
				//System.out.println("Size"+exam.questionList.size());
				int result=exam.calculateResult(exam,exam.questionList.size());					
				//int result=exam.calculateResult(exam,exam.getTotalNumberOfQuestions());
				request.setAttribute("result",result);				
				request.getRequestDispatcher("/WEB-INF/jsps/result.jsp").forward(request,response);
				
			}
			else{
				request.getSession().setAttribute("current", exam.currentQuestion);
			}
			
			//Getting all marked Questions
			int marked = 0;
			for(int i=0;i<exam.getMarkedList().size();i++){
				if(exam.getMark(i)  == "0"){
					marked++;
				}
			}
			request.getSession().setAttribute("markcount", marked);
			
			System.out.println("quiz"+exam.getSelections().get(exam.currentQuestion));
			request.getSession().setAttribute("selectedoption", exam.getSelections().get(exam.currentQuestion));
					
		//Checking if exam submitted	
		if(finish!="1"){
			System.out.println(request);
			System.out.println(response);
			request.getRequestDispatcher("/WEB-INF/jsps/exam.jsp").forward(request,response);
		}
		
	}

}
