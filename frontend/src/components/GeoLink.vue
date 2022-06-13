<template>
    <a target="_blank" :href="link"><slot>Dummy-Text</slot></a>
</template>
<script setup lang="ts">

const props = withDefaults(
    defineProps<{
        lat:number,
        lon:number,
        zoom:number,
    }>(),
    {zoom:17}
);
const link = `https://www.openstreetmap.org/#map=${props.zoom}/${props.lat}/${props.lon}`; 
</script>