package Services;

import Entities.ClientRequest;
import Entities.Template;

public interface NotificationService {

    void sendNotification( ClientRequest request);
}
