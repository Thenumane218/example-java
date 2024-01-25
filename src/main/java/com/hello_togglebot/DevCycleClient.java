package com.hello_togglebot;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;

import com.devcycle.sdk.server.local.api.DevCycleLocalClient;
import com.devcycle.sdk.server.local.model.DevCycleLocalOptions;
import com.devcycle.sdk.server.common.model.DevCycleUser;
import com.devcycle.sdk.server.common.model.BaseVariable;

// Initialize a single DevCycle client to be used across the application
public class DevCycleClient {
	private static final DevCycleLocalClient instance = initialize();

	private DevCycleClient() {}

	private static DevCycleLocalClient initialize() {
		Dotenv dotenv = Dotenv.configure().load();
		String devcycleSdkKey = dotenv.get("DEVCYCLE_SERVER_SDK_KEY");

		if (devcycleSdkKey == null) {
			throw new DotenvException("DEVCYCLE_SERVER_SDK_KEY should be defined in the .env file");
		}

		DevCycleLocalOptions options = DevCycleLocalOptions.builder()
            .configPollingIntervalMs(5000)
            .eventFlushIntervalMS(5000)
            .build();

        return new DevCycleLocalClient(devcycleSdkKey, options);
    }

	public static DevCycleLocalClient getInstance() {
        return instance;
    }

}
