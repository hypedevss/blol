package org.blol.tabcompletions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class portalConfigTab implements TabCompleter {
    private static final String[] args_1 = {"nether", "end"};
    private static final String[] args_2 = {"on", "off"};
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            final List<String> completions = new ArrayList<>();
            StringUtil.copyPartialMatches(args[0], Arrays.asList(args_1), completions);
            return completions;
        }
        if (args.length == 2) {
            final List<String> completions = new ArrayList<>();
            StringUtil.copyPartialMatches(args[1], Arrays.asList(args_2), completions);
            return completions;
        }
        return null;
    }
}
