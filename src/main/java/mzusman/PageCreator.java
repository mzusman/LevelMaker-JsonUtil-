package mzusman;

import java.io.IOException;

/**
 * Created by mzeus-arch on 8/30/16.
 */
public interface PageCreator {
    void addLevel() throws IOException;

    void setBeginOfLevels(int level);

    void addCriteria(String name, String v) throws IOException;

    void endLevel() throws IOException;

    void endLevelsArray() throws IOException;

    int getCurrentLevel();

}
