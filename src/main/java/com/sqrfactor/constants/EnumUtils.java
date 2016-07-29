/**
 * 
 */
package com.sqrfactor.constants;

/**
 * @author Angad Gill
 *
 */
public class EnumUtils {

	public enum FeedAction {
	    Comment  (1), 
	    Like(2),  
	    Share   (3)
	    ; 

	    private final int actionCode;

	    private FeedAction(int actionCode) {
	        this.actionCode = actionCode;
	    }
	    
	    public int getActionCode() {
	        return this.actionCode;
	    }
	}
	
	
	
	public enum NotificationType {
	    ConnectionAdded  (1), 
	    Comment(2),  
	    Like   (3),
	    Share (4)
	    ; 

	    private final int notificationCode;

	    private NotificationType(int notificationCode) {
	        this.notificationCode = notificationCode;
	    }
	    
	    public int getNotificationCode() {
	        return this.notificationCode;
	    }
	}
}
