# 345-group-project
COSC345 - Software Engineering Group Project 2021

## Reading/Research Material
- Generative Adverserial Networks (GAN)
  - [Wiki](https://en.wikipedia.org/wiki/Generative_adversarial_network)
  - The 3 video links found on thispersondoesnotexist explaining how it works: [Link1](https://www.youtube.com/watch?v=u8qPvzk0AfY), [Link2](https://www.youtube.com/watch?v=dCKbRCUyop8), [Link3](https://www.youtube.com/watch?v=SWoravHhsUU) <- I love "Two Minute Papers"
  - Github repo for Nvidia labs [StyleGAN2](https://github.com/NVlabs/stylegan2)
  - Github repo for the Nvidia labs [FFHQ Dataset](https://github.com/NVlabs/ffhq-dataset)
  - Original Paper on [StyleGAN](https://arxiv.org/pdf/1812.04948.pdf) which is what [thispersondoesnotexist](https://thispersondoesnotexist.com/) uses
  - Paper on [improvements made to StyleGAN](https://arxiv.org/pdf/1912.04958.pdf) 
  - Good [video](https://www.youtube.com/watch?v=Sw9r8CL98N0) on GAN by Computerphile
  - [Video](https://www.youtube.com/watch?v=RPGOPrkieTE) on how we can easily generate faces for ourselves
  - Simpler GAN's: [Simple for Pytorch](https://github.com/lucidrains/stylegan2-pytorch), [Lightweight](https://github.com/lucidrains/lightweight-gan)

## Problems To Solve
- Make the network generate faces in a specified age range
- How do we store these images on the app without the app size being very large (<50Mb would be good) 
- Investigate whether it's possible to create the images on the app, perhaps using [TensorFlow Lite](https://www.tensorflow.org/lite)
