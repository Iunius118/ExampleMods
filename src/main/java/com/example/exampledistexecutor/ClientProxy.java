package com.example.exampledistexecutor;

import com.example.exampledistexecutor.ServerProxy;

public class ClientProxy extends ServerProxy {
    @Override
    public void setup() {
        ExampleDistExecutorMod.LOGGER.info("SETUP ON CLIENT");
    }
}
