<template>
  <div class="mimgs">
    <el-carousel :interval="4000" type="card" height="200px"  ref="carousel" @click.native="linkTo">
    <el-carousel-item v-for="item in imgs" :key="item.url">
      <img :src="item.url"/>
    </el-carousel-item>
  </el-carousel>
  </div>
</template>

<script>
export default {
  data() {
    return {
      imgs:[{
          url:require('../assets/logo.png'),
          link:''
        },
        {
          url:require('../assets/logo.png'),
          link:''
        },
        {
          url:require('../assets/logo.png'),
          link:''
        },
        {
          url:require('../assets/logo.png'),
          link:''
        }]
    };
  },
  methods: {
    linkTo(){
      let activeIndex = this.$refs.carousel.activeIndex
      this.$router.push(this.imgs[activeIndex].link)
    }
  }
}
</script>