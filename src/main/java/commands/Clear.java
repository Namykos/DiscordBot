package commands;

import net.dv8tion.jda.core.MessageHistory;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import core.coreCommands;

import java.util.List;

/**
 * Created by zekro on 18.03.2017 / 01:29
 * DiscordBot / commands
 * © zekro 2017
 */



public class Clear implements Command {

    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    public static String HELP = ":warning:  USAGE: ` ~clear <amount ob messages (>2)> to clear an amount of chat messages`";

    public void action(String[] args, MessageReceivedEvent event) {

        if (!coreCommands.checkPermission(event)) {
            event.getTextChannel().sendMessage(
                    ":warning:  Sorry, " + event.getAuthor().getAsMention() + ", you don't have the permissions to use this command!"
            ).queue();
            return;
        }

        if (args.length > 0) {
            if (Integer.parseInt(args[0]) > 1) {

                MessageHistory history = new MessageHistory(event.getTextChannel());
                List<Message> msgs;

                try {
                    msgs = history.retrievePast(Integer.parseInt(args[0])).block();
                    event.getTextChannel().deleteMessages(msgs).queue();

                    msgs = history.retrievePast(2).block();
                    event.getTextChannel().deleteMessageById(msgs.get(0).getId()).queue();

                    event.getTextChannel().sendMessage(args[0]+ " Messages deleted!").queue();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (Integer.parseInt(args[0]) == 1) {

                MessageHistory history = new MessageHistory(event.getTextChannel());
                List<Message> msgs;

                try {
                    msgs = history.retrievePast(1).block();
                    event.getTextChannel().deleteMessageById(msgs.get(0).getId()).queue();

                    msgs = history.retrievePast(2).block();
                    event.getTextChannel().deleteMessageById(msgs.get(0).getId()).queue();

                    event.getTextChannel().sendMessage(args[0]+ " Message deleted!").queue();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }




        //if (args.length == 0) {
        //    event.getTextChannel().sendMessage(help()).queue();
        //} else if (Integer.parseInt(args[0]) < 2) {
        //    event.getTextChannel().sendMessage(help()).queue();
        //}



        MessageHistory history = new MessageHistory(event.getTextChannel());
        List<Message> msgs;

        MessageHistory historyAfter = new MessageHistory(event.getTextChannel());

        try {
            Thread.sleep(5000);

            msgs = historyAfter.retrievePast(1).block();
            event.getTextChannel().deleteMessageById(msgs.get(0).getId()).queue();
        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    public void executed(boolean success, MessageReceivedEvent event) {

    }

    public String help() {
        return HELP;
    }

    @Override
    public String description() {
        return "Bulk delete chat messages";
    }
}
