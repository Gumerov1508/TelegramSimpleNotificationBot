package org.example.NotificationBot.cash;

import org.example.NotificationBot.model.BotState;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Getter
@Setter
public class BotStateCash {
    private final Map<Long, BotState> botStateMap = new HashMap<>();

    public void saveBotState(long userId, BotState botState){
        botStateMap.put(userId, botState);
    }
}
