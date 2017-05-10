# TestSharedElement
Samsung Nougat 7.0 Shared Element Issue

## Issue
There appears to be an animation issue when doing a shared element transition between activies where you're animating a parent layout and one or more of it's children.
![Screenshot](/Screenshot_20170508-141744.png)

## Cause
The coordinates of the child views in the onSharedElementStart method are still mapped to their parent and not the root.

| Device | Source Activity | Destination Activity |
| --- | --- | --- |
| 7.0 Emulator | image_one: x: 333.0, y: 140.0 | onSharedElementStart: image_one, x: 560.0, y: 998.0 |
| S6 Nougat 7.0 | image_one: x: 333.0, y: 140.0 | onSharedElementStart: image_one, x: 333.0, y: 140.0 |

Notice on the S6, in onSharedElementStart the X and Y coordinate is the same as the source activity, but on the emulator they are the coordinates of the view within the root.
