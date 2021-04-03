package com.sbss.bithon.server.common.utils;

import com.sbss.bithon.component.db.dao.EndPointType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jooq.tools.StringUtils;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/3/22
 */
public class MiscUtils {

    @Getter
    @AllArgsConstructor
    public static class ConnectionString {
        private final String hostAndPort;
        private final String database;
        private final EndPointType endPointType;
    }

    public static ConnectionString parseConnectionString(String connectionString) {
        if (StringUtils.isBlank(connectionString)) {
            throw new RuntimeException(String.format("Connection String of SqlMetricMessage is blank: [%s]",
                                                     connectionString));
        }

        if (!connectionString.startsWith("jdbc:")) {
            throw new RuntimeException(String.format("Unknown format of Connection String: [%s]", connectionString));
        }

        try {
            URI uri = new URI(connectionString.substring(5));
            switch (uri.getScheme()) {
                case "h2":
                    return new ConnectionString("localhost", uri.getSchemeSpecificPart(), EndPointType.DB_H2);
                case "mysql":
                    return new ConnectionString(uri.getHost() + ":" + uri.getPort(),
                                                uri.getPath().substring(1),
                                                EndPointType.DB_MYSQL);
                default:
                    throw new RuntimeException(String.format("Unknown schema of Connection String: [%s]",
                                                             connectionString));
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(String.format("Invalid format of Connection String: [%s]", connectionString));
        }
    }
}