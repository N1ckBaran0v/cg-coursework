package com.github.N1ckBaran0v.program;

public class DrawCommand implements Command {
    private final String cameraName;

    public DrawCommand(String cameraName) {
        this.cameraName = cameraName;
    }

    @Override
    public void execute(Context context) {
        var artist = context.getArtist();
        artist.draw(cameraName);
    }
}
