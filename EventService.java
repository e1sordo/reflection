package com.vlavik.reflection;

import java.lang.reflect.Field;

/**
 * Created on 28.02.2018.
 * @author vlavik
 */
public class EventService {

    private final Event oldEvent = new Event("Vova", "15.02", "18.02", "SPb", "999021");
    private final Event newEvent = new Event("Sasha", "15.02", "19.02", "SPb", "999021");

    public static void main(String... args) {
        EventService es = new EventService();
        es.updateEvent(es.newEvent);
    }

    public Event updateEvent(Event newEvent) {
        // .....
        StringBuilder messageBody = prepareMessageBody(newEvent, oldEvent);
        sendEmail(messageBody);
        System.out.println(messageBody);
        return newEvent;
    }

    private StringBuilder prepareMessageBody(Event newEvent, Event oldEvent) {
        StringBuilder messageBody = new StringBuilder();
        messageBody.append("Dear Epamer. The event you are subscribed to has been changed. List of changes: \n");
        appendInfoAboutChangedFields(messageBody, newEvent, oldEvent);
        // append Event URL
        return messageBody;
    }

    private void appendInfoAboutChangedFields(StringBuilder messageBody, Event newEvent, Event oldEvent) {
        Field[] fields = Event.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ImportantForUser.class)) {
                field.setAccessible(true);
                try {
                    Object oldFieldValue = field.get(oldEvent);
                    Object newFieldValue = field.get(newEvent);
                    if (!oldFieldValue.equals(newFieldValue)) {
                        messageBody.append(fieldChangeWrapper(field, newFieldValue, oldFieldValue));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } finally {
                    field.setAccessible(false);
                }
            }
        }
    }

    private String fieldChangeWrapper(Field field, Object newFieldValue, Object oldFieldValue) {
        StringBuilder wrapper = new StringBuilder();
        wrapper.append("[NEW]");
        wrapper.append(field.getName());
        wrapper.append('\n');
        wrapper.append(oldFieldValue.toString());
        wrapper.append(" --> ");
        wrapper.append(newFieldValue.toString());
        wrapper.append('\n');
        return wrapper.toString();
    }

    private void sendEmail(StringBuilder messageBody) {
        StringBuilder messageHeader = new StringBuilder();
        // sendEmail
    }

}
