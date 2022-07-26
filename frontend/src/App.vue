<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import HelloWorld from '@/components/HelloWorld.vue'
</script>

<template>
  <header>
    <H1>Treffliche Angebote</H1>
    <div class="wrapper">
      <nav>
        <RouterLink to="/">Home</RouterLink><br>
           
        <RouterLink to="/about">About</RouterLink><br>
      </nav>
    </div>
  </header>

  <RouterView/>
</template>

<style>

</style>
