package com.example.exampleconfig;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.List;

public class ExampleConfig {
    public static class Common {
        // 例えばこの値をmod内から取得するには ExampleConfig.COMMON.booleanValue.get()
        public final BooleanValue booleanValue;
        public final IntValue intValue;
        public final LongValue longValue;
        public final DoubleValue doubleValue;
        public final EnumValue<Pet> enumValue;
        public final ConfigValue<String> stringValue;
        public final ConfigValue<String> stringValueFromList;
        public final ConfigValue<List<Integer>> intList;

        Common(ForgeConfigSpec.Builder builder) {
            builder.comment("Common settings")
                    .push("common");    // 以下[common]内の項目

            booleanValue = builder
                    .comment("A boolean value")
                    .define("booleanValue", true);

            intValue = builder
                    .comment("An integer value")
                    .defineInRange("intValue", 1, -100, 100);

            longValue = builder
                    .comment("A long value")
                    .defineInRange("longValue", 10000000000L, -100000000000L, 100000000000L);

            doubleValue = builder
                    .comment("A double value")
                    .defineInRange("doubleValue", 1.5, -100., 100.);

            builder.comment("Sub configuration")
                    .push("sub"); // 以下[common.sub]内の項目

            enumValue = builder
                    .comment("An enum value")
                    .defineEnum("enumValue", Pet.SQUID);

            stringValue = builder
                    .comment("A string value")
                    .define("stringValue", "Ika-chan");

            stringValueFromList = builder
                    .comment("A string value from the list [A, B, C]")
                    .defineInList("stringValueFromList", "A", Arrays.asList("A", "B", "C"));

            intList = builder
                    .comment("A list of integer values")
                    .define("intList", Arrays.asList(1, 2, 4, 8, 16, 32, 64));

            builder.pop();  // ここまで[common.sub]内の項目

            builder.pop();  // ここまで[common]内の項目
        }

        public enum Pet {
            CAT,
            PARROT,
            SQUID,
            WOLF
        }
    }

    public static class Server {
        public final IntValue intValueInServer;

        Server(ForgeConfigSpec.Builder builder) {
            builder.comment("Server only settings")
                    .push("server");    // 以下[server]内の項目

            intValueInServer = builder
                    .comment("An integer value in server")
                    .defineInRange("intValueInServer", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

            builder.pop();  // ここまで[server]内の項目
        }
    }

    public static class Client {
        public final IntValue intValueInClient;

        Client(ForgeConfigSpec.Builder builder) {
            builder.comment("Client only settings")
                    .push("client");    // 以下[client]内の項目

            intValueInClient = builder
                    .comment("An integer value in client")
                    .defineInRange("intValueInClient", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

            builder.pop();  // ここまで[client]内の項目
        }
    }

    static final ForgeConfigSpec commonSpec;
    public static final Common COMMON;

    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        commonSpec = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    static final ForgeConfigSpec serverSpec;
    public static final Server SERVER;

    static {
        final Pair<Server, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Server::new);
        serverSpec = specPair.getRight();
        SERVER = specPair.getLeft();
    }

    static final ForgeConfigSpec clientSpec;
    public static final Client CLIENT;

    static {
        final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
        clientSpec = specPair.getRight();
        CLIENT = specPair.getLeft();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading event) {
        // Configファイルがロードされた
        ExampleConfigMod.LOGGER.info("Loaded example config file {}", event.getConfig().getFileName());
    }

    @SubscribeEvent
    public static void onFileChange(final ModConfig.ConfigReloading event) {
        // Configファイルが変更された
        ExampleConfigMod.LOGGER.info("Changed example config file {}", event.getConfig().getFileName());
    }
}
