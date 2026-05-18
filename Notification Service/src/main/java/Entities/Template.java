package Entities;

import Enums.ChannelEnum;
import Enums.TemplateNameEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Builder
@Setter
@Getter
public class Template {

        private String templateID;
        private TemplateNameEnum templateName;
        private ChannelEnum channel;
        private String templateBody;

}
