package com.ninlgde.advanced.astar.profile;

/**
 * @author ninlgde
 * @date 2022/9/23 12:04
 */
public class ProfileNode {
    public long end;
    public final String title;
    public long start;
    public long duration;
    public long max = 0L;
    public long min = 9223372036854775807L;
    public long fst;
    public long cnt;
    public long sum;

    public ProfileNode(String title) {
        this.title = title;
    }

    public void submit() {
        this.sum += this.duration;
        if (this.cnt++ == 0L) {
            this.fst = this.duration;
        }

        if (this.duration < this.min) {
            this.min = this.duration;
        }

        if (this.duration > this.max) {
            this.max = this.duration;
        }

        this.start = 0L;
        this.end = 0L;
        this.duration = 0L;
    }

    public void reset() {
        this.start = 0L;
        this.end = 0L;
        this.duration = 0L;
    }
}
