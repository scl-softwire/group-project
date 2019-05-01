package org.softwire.training.groupproject.services;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.groupproject.DatabaseConfiguration;

public abstract class DatabaseService {
    protected final Jdbi jdbi = Jdbi.create(new DatabaseConfiguration().getConnectionString());
}
