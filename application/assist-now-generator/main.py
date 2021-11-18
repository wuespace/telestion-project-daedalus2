import time
import base64

title = "Testnachricht" + "\n"
message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam felis turpis, auctor quis suscipit id, \
dapibus nec nibh. Nunc eget leo dictum, eleifend erat et, sagittis dolor. Nulla facilisi. Quisque lacinia maximus \
nulla in gravida. Ut dapibus aliquet luctus. Quisque quis vehicula odio, ut tincidunt orci. Orci varius natoque \
penatibus et magnis dis parturient montes, nascetur ridiculus mus. Morbi massa justo, mattis sed eleifend vitae, \
malesuada id risus. Quisque vestibulum faucibus sagittis. In tellus tellus, suscipit eu convallis ut, auctor ut \
augue. Maecenas quis neque ex. Pellentesque lacinia laoreet nisl, et lacinia elit molestie at. Praesent eleifend diam \
at mauris pellentesque luctus."

time = time.strftime("%Y-%m-%d-%H_%M_%S%z")
raw = message.encode("ascii")
encoded = base64.b64encode(raw)

# write file
file = open("assist-now-" + time + ".bin", "wb")
file.write(title.encode("ascii"))
file.write(encoded)
file.close()
