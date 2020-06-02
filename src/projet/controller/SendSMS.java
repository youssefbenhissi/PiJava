/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

/**
 *
 * @author youssef
 */
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
public class SendSMS {
    public static final String ACCOUNT_SID = "ACfbd03ebad1bc7be3f64db3401386c998";
    public static final String AUTH_TOKEN = "b50b3094e60e4cbebb044b30e7c8c0ba";

    public static void sendSMSreservation() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(new com.twilio.type.PhoneNumber("+21655378936"),//to
                new com.twilio.type.PhoneNumber("+17372456684"),//from 
                "un commentaire a été ajouté").create();
    }
}