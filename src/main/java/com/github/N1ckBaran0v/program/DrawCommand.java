package com.github.N1ckBaran0v.program;

public class DrawCommand extends Command {
    private final String cameraName;

    public DrawCommand(String cameraName) {
        this.cameraName = cameraName;
    }

    @Override
    public void execute() {
        super.execute();
        var artist = context.getArtist();
        artist.draw(cameraName);
    }
}
