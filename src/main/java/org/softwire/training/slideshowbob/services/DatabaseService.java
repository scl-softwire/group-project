package org.softwire.training.slideshowbob.services;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.slideshowbob.DatabaseConfiguration;

public abstract class DatabaseService {
    protected final Jdbi jdbi = Jdbi.create(new DatabaseConfiguration().getConnectionString());
}
