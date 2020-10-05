package com.mycompany.usernumber;

import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.LongRangeValidator;
import javax.faces.validator.ValidatorException;

public class SecretNumber  {

	private int maximum = 100;
	private int minimum = 1;
	private String[] status = null;
         private int remainingGuesses = 7;
	private String response = null;
	private Integer randomInt = null;
	private Integer userNumber = null;
	private boolean btnV = false;
        private Integer  Score = 0  ;

    public Integer getScore() {
        return Score;
    }

    public void setScore(Integer Score) {
        this.Score = Score;
    }
	

	// Generating Random Number At Application Start-Up Which Will Be Used To Test The Application
	public SecretNumber() {
		Random randomNum = new Random();
		do {
             randomInt = 1+ randomNum.nextInt(100);
		} while (randomInt.intValue() == 0);
		System.out.println("Selected Random Number Is?: " + randomInt);
	}

    public int getRemainingGuesses() {
        return remainingGuesses;
    }

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
		
	}

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	
	}

	public String[] getStatus() {
		return status;
	}

	public void setStatus(String[] status) {
		this.status = status;
	}

	// Check Whether The Entered Number Is Correct Or Incorrect. 
	public String getResponse() {	
              
		if (userNumber != null && userNumber.compareTo(randomInt) == 0) {
			setBtnV(true);
                       
                        
                        
			response = "Congratulations! You got the correct number   !";
		} else if (userNumber == null) {
                    
			response = "Sorry, " + userNumber + " is incorrect. Try again with  big  number.";
		} else {
                         
                        
			int enteredNum = userNumber.intValue();
			System.out.println("Number Entered By User Is?= " + enteredNum);
			if (enteredNum > randomInt.intValue()) {
                           
				response = "Sorry, " + userNumber + " is incorrect. Try again with a  small number.";
			} else {
				response = "Sorry, " + userNumber +" is incorrect. Try again with a  big number.";
                                
                        }
                        
                        remainingGuesses--;
                        Score = Score + (remainingGuesses * 10) + 100  ;
 		}
               
		return response;
	}
          

	public Integer getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(Integer userNumber) {
		this.userNumber = userNumber;
	}

	public boolean isBtnV() {
		return btnV;
	}

	public void setBtnV(boolean btnValue) {
		this.btnV = btnValue;
	}

	

	
	private int intValue(Object attributeValue) throws NumberFormatException {
		if (attributeValue instanceof Number) {
			return ((Number) attributeValue).intValue();
		} else {
			return Integer.parseInt(attributeValue.toString());
		}
	}
        
        


            public void validateNumberguess(FacesContext context, 
                                    UIComponent toValidate, 
                                    Object value) {
        if (remainingGuesses <= 0) {
            FacesMessage message = new FacesMessage(" vous avez dépassé le nombre de tentatives ");
            context.addMessage(toValidate.getClientId(context), message);
            ((UIInput) toValidate).setValid(false);
            return;
        }

    }
}