### VM Args:
#### common
```-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8```
    
    
#### heap oom
```-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError```
    
#### Java VM Stack
1. StackOverFlow

```-Xss128k```
2. OOM

```-Xss2m```