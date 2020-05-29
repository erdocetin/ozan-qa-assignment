package departments.storage;

import departments.VisitedLink;

import java.io.File;
import java.util.Collection;

public interface LinkStatusSaver {
    File save(Collection<VisitedLink> links) throws Exception;
}
