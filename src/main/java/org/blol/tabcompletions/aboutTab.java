package org.blol.tabcompletions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class aboutTab implements TabCompleter {
    private static final String[] args2 = {"about", "reload"};
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

            final List<String> completions = new ArrayList<>();
            StringUtil.copyPartialMatches(args[0], Arrays.asList(args2), completions);
            return completions;
    }
}
