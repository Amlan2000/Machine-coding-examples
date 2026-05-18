package Services;

import Entities.Template;
import Enums.ChannelEnum;
import Enums.TemplateNameEnum;

import java.util.HashMap;
import java.util.Map;

public interface TemplateResolutionService {

     void setTemplate(Template template);
     String resolveTemplate(TemplateNameEnum templateName, ChannelEnum channel, Map<String, String> parameters);
}
