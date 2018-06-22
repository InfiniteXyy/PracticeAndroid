package cn.infinitex.xyy.androidpractice.practice.log;

public class Log {
    public int id;
    public long create_time;
    public String log;

    public Log(int id, long create_time, String log) {
        this.id = id;
        this.create_time = create_time;
        this.log = log;
    }

    @Override
    public String toString() {
        return "id:" + id + "\ncreate_time:" + create_time + "\ncontent:" + log;
    }
}
