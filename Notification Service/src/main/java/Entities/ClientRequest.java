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
public class ClientRequest {

    private TemplateNameEnum templateName;
    private Customer recipientInfo;

    @Builder.Default
    private HashMap<String,String> templateParametersMap = new HashMap<>();

    private ChannelEnum channel;
    private String messageType;
}
