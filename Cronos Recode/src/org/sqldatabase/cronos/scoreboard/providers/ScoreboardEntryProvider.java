package org.sqldatabase.cronos.scoreboard.providers;

import org.bukkit.entity.*;
import java.util.*;

public interface ScoreboardEntryProvider
{
    List<String> getScoreboardEntries(final Player p0);
}
