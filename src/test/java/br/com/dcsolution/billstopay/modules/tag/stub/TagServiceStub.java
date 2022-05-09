package br.com.dcsolution.billstopay.modules.tag.stub;

import br.com.dcsolution.billstopay.modules.tag.dto.TagDto;
import br.com.dcsolution.billstopay.modules.tag.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

public final class TagServiceStub {
    public static final String NAME = "free-card";
    public static final Integer ID = 1;
    public static final Integer PAGE = 1;
    public static final Integer SIZE = 10;

    public static TagDto generateDto() {
        final TagDto tagDto = new TagDto();
        tagDto.setId(ID);
        tagDto.setName(NAME);
        return tagDto;
    }

    public static TagDto generateDtoParameter(final Integer id, final String name) {
        final TagDto tagDto = new TagDto();
        tagDto.setId(id);
        tagDto.setName(name);
        return tagDto;
    }

    public static Tag generateEntity() {
        final Tag tag = new Tag();
        tag.setId(ID);
        tag.setName(NAME);
        return tag;
    }

    public static Page<Tag> generatePageEntity() {
        final List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(ID, NAME));

        return new PageImpl<>(tags);
    }

    public static Page<TagDto> generatePageDto() {
        final List<TagDto> tags = new ArrayList<>();
        tags.add(new TagDto(ID, NAME));

        return new PageImpl<>(tags);
    }

    public static PageRequest generatePageRequest() {
        return PageRequest.of(0, 10);
    }

}
